package and5.abrar.challenge_ch4

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var id : Int?,
    @ColumnInfo(name = "title") var title : String,
    @ColumnInfo(name = "text") var text : String
) : Parcelable
