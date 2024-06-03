package buyticket.auth.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "session", schema = "public")
data class Session(

    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "user_id")
    var userId: Int = 0,

    @Column(name = "token")
    var token: String = "",

    @Column(name = "expires")
    var expires: Timestamp = Timestamp(System.currentTimeMillis() + 1000 * 60 * 60),
)