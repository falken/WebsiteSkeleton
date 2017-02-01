package com.falkenproject.wsskel.web;


import com.falkenproject.wsskel.dao.SkeletonDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Path("/api/v0/skeleton")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SkeletonResource {

    private Map<String,SkeletonObject> objects = new HashMap<>();
    private SkeletonDAO skeletonDAO;

    public SkeletonResource(SkeletonDAO skeletonDAO){

        this.skeletonDAO = skeletonDAO;
    }

    @GET
    public Collection<SkeletonObject> getSkeletonObjects(){
        return skeletonDAO.getObjects();
    }


    @POST
    public void addSkeleton(SkeletonObject skeletonObject){
        skeletonDAO.saveObject(skeletonObject);
    }

    @GET
    @Path("/{name}")
    public SkeletonObject getSkeletonObject(@PathParam("name") String name){
        return skeletonDAO.getObject(name);
    }

    @DELETE
    @Path("/{name}")
    public void deleteSkeletonObject(@PathParam("name") String name){
        skeletonDAO.deleteObject(name);
    }

}
