package pr.eleks.we_at_her.exceptions

class UUIDNotFoundException(
        uuidString: String
) : Exception("User with UUID \"$uuidString\" was not found.")