package ir.smartech.cro.storage.data.postgres.repository

import ir.smartech.cro.storage.data.postgres.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}