package pr.eleks.we_at_her.exceptions

class WrongApiResponseException(expectedClass: String) : Exception("Obtained wrong api response. Expected class: $expectedClass")
