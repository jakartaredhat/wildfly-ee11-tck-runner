package shrinkwrap;

import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenFormatStage;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolvedArtifact;
import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenCoordinate;
import org.junit.jupiter.api.Test;

public class LibResolveTest {
    @Test
    public void resolveCommonsLib() {
        String[] activeMavenProfiles = {"staging"};
        MavenResolvedArtifact[] resolvedArtifacts = Maven.resolver().loadPomFromFile("pom.xml", activeMavenProfiles)
                .resolve("org.apache.commons:commons-lang3:3.9")
                .withTransitivity()
                .asResolvedArtifact();
        MavenResolvedArtifact resolvedArtifact = resolvedArtifacts[resolvedArtifacts.length - 1];
        System.out.println("Resolved artifact: " + resolvedArtifact.asFile());
    }
    @Test
    public void resolveCommonsLib2() {
        String[] activeMavenProfiles = {"staging"};
        MavenResolvedArtifact resolvedArtifact = Maven.resolver().loadPomFromFile("pom.xml", activeMavenProfiles)
                .resolve("org.apache.commons:commons-lang3:3.9")
                .withTransitivity()
                .asSingleResolvedArtifact();
        System.out.println("Resolved artifact: " + resolvedArtifact.asFile());
    }

    @Test
    public void resolveProtocolLib() {
        String[] activeMavenProfiles = {"staging"};
        MavenResolvedArtifact[] resolvedArtifacts = Maven.resolver().loadPomFromFile("pom.xml", activeMavenProfiles)
                .resolve("jakarta.tck.arquillian:arquillian-protocol-lib:1.0.0-SNAPSHOT")
                .withTransitivity()
                .asResolvedArtifact();
        MavenResolvedArtifact protocolLib = null;
        for (MavenResolvedArtifact resolvedArtifact : resolvedArtifacts) {
            MavenCoordinate gav = resolvedArtifact.getCoordinate();
            if(gav.getGroupId().equals("jakarta.tck.arquillian") && gav.getArtifactId().equals("arquillian-protocol-lib")) {
                protocolLib = resolvedArtifact;
                break;
            }
        }
        System.out.println("Resolved artifact: " + protocolLib);
    }
}
