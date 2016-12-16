package sample

class Node() {

  lateinit var name: String
  lateinit var children: MutableList<Node>
  var expanded: Boolean = false
  var matched: Boolean = false

  fun search(s: String): Node? {
    // Check if node itself matches
    if (name.contains(s, true)) {
      // check child, and even it does not match not expanded - don't drop it because
      // we have to keep all descendants of matched node
      val traversedChildren = children.map { it.search(s) ?: it }

      return Node().apply {
        name = this@Node.name
        matched = true
        expanded = traversedChildren.any { it.expanded || it.matched }
        children = traversedChildren.toMutableList()
      }
    } else {
      val traversedChildren = children.mapNotNull { it.search(s) }
      if (traversedChildren.isEmpty()) {
        return null
      }
      return Node().apply {
        name = this@Node.name
        expanded = true
        children = traversedChildren.toMutableList()
      }
    }
  }

  override fun toString(): String {
    return name
  }
}