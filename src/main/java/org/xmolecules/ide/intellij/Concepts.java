/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xmolecules.ide.intellij;

import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Oliver Drotbohm
 */
public class Concepts {
	public static final Concept FACTORY = new Concept("Factory", "Factories", null);
	public static final Concept SERVICE = new Concept("Service", "Services", null);
	public static final Concept REPOSITORY = new Concept("Repository", "Repositories", null);
	public static final Concept IDENTIFIER = new Concept("Identifier", "Identifiers", null);
	public static final Concept VALUE_OBJECT = new Concept("Value Object", "Value objects", null);
	public static final Concept ENTITY = new Concept("Entity", "Entities", null);
	public static final Concept AGGREGATE_ROOT = new Concept("Aggregate Root", "Aggregate roots", null);
	public static final Concept EVENT = new Concept("Event", "Events", null);
	public static final Concept EVENT_LISTENER = new Concept("Event listener", "Event listeners", null);

	/*
	object ApplicationLayer: Concept("ApplicationLayer")
object DomainLayer: Concept("DomainLayer")
object InfrastructureLayer: Concept("InfrastructureLayer")
object InterfaceLayer: Concept("InterfaceLayer")
object ClassicApplicationServiceRing: Concept("ClassicApplicationServiceRing")
object ClassicDomainModelRing: Concept("ClassicDomainModelRing")
object ClassicInfrastructureRing: Concept("ClassicInfrastructureRing")
object SimplifiedApplicationRing: Concept("SimplifiedApplicationRing")
object SimplifiedDomainRing: Concept("SimplifiedDomainRing")
object SimplifiedInfrastructureRing: Concept("SimplifiedInfrastructureRing")

	 */

	public static Map<Concept, List<PsiFile>> groupByConcept(final Collection<PsiFile> files) {
		var result = new TreeMap<Concept, List<PsiFile>>();

		for (final PsiFile file : files) {
			for (final Concept concept : getConcepts(file)) {
				result.computeIfAbsent(concept, __ -> new ArrayList<>()).add(file);
			}
		}

		return result;
	}

	public static List<Concept> getConcepts(final PsiFile file) {

		if (!(file instanceof PsiJavaFile)) {
			return Collections.emptyList();
		}

		return CachedValuesManager.getCachedValue(file,
				() -> new CachedValueProvider.Result<>(All.ALL.stream()
					.filter(it -> it.test((PsiJavaFile) file))
					.map(ConceptImplementation::getConcept)
					.collect(Collectors.toList()), file)
		);
	}

	static class Predicates {

		@SafeVarargs
		static <T> Predicate<T> any(Predicate<T>... predicates) {
			return it -> Arrays.stream(predicates).anyMatch(predicate -> predicate.test(it));
		}
	}
}
