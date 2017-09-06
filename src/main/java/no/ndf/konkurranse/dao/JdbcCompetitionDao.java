package no.ndf.konkurranse.dao;

import no.ndf.konkurranse.rest.Competition;
import no.ndf.konkurranse.rest.Dancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by gloken on 20.12.2015.
 */
@Repository
public class JdbcCompetitionDao implements CompetitionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createCompetition(String competitionName) {
        try {
            Competition competition = getCompetition(competitionName);
        } catch (Exception e) {
            String sql = "insert into competition (name) values(?)";
            this.jdbcTemplate.update(sql, competitionName);
        }

    }

    @Override
    public Competition getCompetition(String competitionName) {
        String sql = "select name from competition where name = ?";

        Competition competition = this.jdbcTemplate.queryForObject(sql, new Object[] {competitionName}, new RowMapper<Competition>() {
            @Override
            public Competition mapRow(ResultSet resultSet, int i) throws SQLException {
                Competition competition = new Competition(resultSet.getString("name"));
                return competition;
            }
        });

        return competition;
    }

    @Override
    public void addDancers(String competitionName, List<Dancer> dancers) {

    }
}
