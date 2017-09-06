package no.ndf.konkurranse.dao;

import no.ndf.konkurranse.rest.Competition;
import no.ndf.konkurranse.rest.Dancer;

import java.util.List;

/**
 * Created by gloken on 20.12.2015.
 */
public interface CompetitionDao {
    void createCompetition(String competitionName);

    Competition getCompetition(String competitionName);

    void addDancers(String competitionName, List<Dancer> dancers);
}
