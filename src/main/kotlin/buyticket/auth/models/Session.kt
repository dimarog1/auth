package buyticket.auth.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "session", schema = "public")
data class Session(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User? = null,

    @Column(name = "token")
    var token: String = "",

    @Column(name = "expires")
    var expires: Timestamp = Timestamp(System.currentTimeMillis() + 1000 * 60 * 60),
)