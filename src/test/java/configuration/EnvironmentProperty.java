package configuration;

import lombok.extern.slf4j.Slf4j;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class EnvironmentalProperty {

    private final String APP_ENV;
    private final BrowserEnvironment BROWSER_ENV;

    public static EnvironmentalProperty getInstance(){
        return EnvironmentalProperty.EnvironmentPropertySingleton.INSTANCE;
    }
    private void initEnv(){
        if(!this.APP_ENV.isEmpty()){
            log.debug(" >>>>>>>>>>>>>>>>>>>>> Environmental name: " + this.APP_ENV);
            loadAllEnvPropertiesToSystem(this.APP_ENV);
        }else {
            log.error("Please provide env name");
            assertThat(true, equalTo(false));
        }
    }
    private static class EnvironmentPropertySingleton{
        private static final EnvironmentalProperty INSTANCE = new EnvironmentalProperty();
    }

}
