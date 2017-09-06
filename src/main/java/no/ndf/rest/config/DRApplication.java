package no.ndf.rest.config;

import no.ndf.konkurranse.rest.DancerResource;
import no.ndf.konkurranse.rest.FileUploadResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by gloken on 19.11.2015.
 */
public class DRApplication extends ResourceConfig {
    public DRApplication() {
        super(DancerResource.class,
              FileUploadResource.class,
              MultiPartFeature.class,
                JacksonFeature.class);
    }
}
