package and5.abrar.challenge_ch4

import androidx.room.*

@Dao
interface UserDao {
@Insert fun insertUser(user: User):Long
@Query ("SELECT * FROM User ") fun getAllUser() : List<User>
@Delete fun deleteUser(user: User): Int
@Update fun updateUser(user: User): Int
}