package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

import static com.epam.rd.izh.util.StringConstants.*;

@Repository
public class UserRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  /**
   * Ищет пользователя по логину в базе
   */
  @Nullable
  public AuthorizedUser getAuthorizedUserByLogin(@Nonnull String login) {
    String sqlRequest = "SELECT id, login, password, role, UUID FROM finalprojectdatabase.autorizeduser WHERE login = '?" + login + "';";
    List<AuthorizedUser> authorizedUsers = jdbcTemplate.query(sqlRequest, (resultSet, numRow) ->
            new AuthorizedUser(
                    resultSet.getString(LOGIN),
                    resultSet.getString(PASSWORD),
                    resultSet.getString(ROLE),
                    UUID.fromString(resultSet.getString(UUIDFORUSER))));
    return authorizedUsers.get(0);
  }

  /**
   * Добавляет пользователя в базу
   */
  public void addAuthorizedUser(AuthorizedUser user) {
    String sqlRequest = "INSERT INTO finalprojectdatabase.autorizeduser(login, password, role, UUID) VALUES ('123', '$2a$10$WGKZMoYLKThDtLG/Kupdp.BsIA24Pwoy834qmovIdflZjzGEqZQWW', 'user', 'fc86d7f0-5473-4803-9ba2-ad60f02764b0')";
    jdbcTemplate.execute(sqlRequest);
  }

  /**
   * Редактирует пользователя в базе
   */
  public boolean editAuthorizedUser(@Nullable String login, String column, @Nullable String newString) {
    String sqlRequest = "UPDATE finalprojectdatabase.autorizeduser SET '?" + column + "' = '?" + newString + "' WHERE login = ?'" + login + "'";
    jdbcTemplate.execute(sqlRequest);
    return false;
  }

  /**
   * Удаляет пользователя из базы
   */
  public boolean deleteAuthorizedUser(@Nullable String login) {
    String sqlRequest = "DELETE FROM finalprojectdatabase.autorizeduser(id, login, password, role) WHERE login = '?" + login + "'";
    jdbcTemplate.execute(sqlRequest);
    return true;
  }
}