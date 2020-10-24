package nexos.intellij.ddd

open class Concept(val name: String, val defaultColorName:String? = null)

object Entity: Concept("Entity")
object AggregateRoot: Concept("AggregateRoot")
object BoundedContext: Concept("BoundedContext")
object Factory: Concept("Factory")
object Module: Concept("Module")
object Repository: Concept("Repository")
object Service: Concept("Service")
object ValueObject: Concept("ValueObject")
object DomainEvent: Concept("DomainEvent")
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