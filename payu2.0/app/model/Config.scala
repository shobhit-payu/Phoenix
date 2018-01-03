package model

import java.sql.Timestamp

/**
  * Created by pragya.mishra on 1/3/18.
  */
case class Config (
                    id  : Int,
                    key : String,
                    value : String,
                    version : Int,
                    isActive : Int,
                    addedOn : Timestamp,
                    updatedOn : Timestamp
)


