package com.pandhu.recsys.resources;

import com.pandhu.recsys.db.LeaderboardDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by wahyuoi on 10/26/15.
 */
@Path("/leaderboard")
@Produces(MediaType.APPLICATION_JSON)
public class LeaderboardResource {
    private final LeaderboardDAO leaderboardDAO;

    public LeaderboardResource(LeaderboardDAO leaderboardDAO) {
        this.leaderboardDAO = leaderboardDAO;
    }

    @GET
    @UnitOfWork
    public Response getLeaderboard(){
        return Response.ok(leaderboardDAO.getAll()).build();
    }
}
