package pr.eleks.we_at_her.services

interface EmailService {
    fun sendEmail(to: String, subject: String, text: String)
}