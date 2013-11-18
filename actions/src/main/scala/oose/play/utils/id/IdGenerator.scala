package oose.play.utils.id

trait IdGenerator {
  type Id
  def id: Id
}

trait UUIDGenerator extends IdGenerator {
  type Id = String
  val id: Id = java.util.UUID.randomUUID().toString()
}