package buyticket.auth.models

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
@Table(name = "user", schema = "public")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "nickname")
    var nickname: String = "",

    @Column(name = "password")
    var password: String = "",

    @Column(name = "email")
    var email: String = "",

    @Column(name = "created")
    var created: Timestamp = Timestamp(System.currentTimeMillis()),
)