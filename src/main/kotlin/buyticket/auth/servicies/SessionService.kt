package buyticket.auth.servicies

import buyticket.auth.repositories.SessionRepository
import org.springframework.stereotype.Service

@Service
class SessionService(private val sessionRepository: SessionRepository) {

    fun findByUserId(userId: Int): buyticket.auth.models.Session? {
        return sessionRepository.findByUserId(userId)
    }

    fun findByToken(token: String) : buyticket.auth.models.Session? {
        return sessionRepository.findByToken(token);
    }

    fun save(session: buyticket.auth.models.Session): buyticket.auth.models.Session {
        return sessionRepository.save(session)
    }
}