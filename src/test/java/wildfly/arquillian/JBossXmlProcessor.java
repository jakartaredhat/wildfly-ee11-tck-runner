package wildfly.arquillian;

import org.jboss.arquillian.config.descriptor.api.ArquillianDescriptor;
import org.jboss.arquillian.config.descriptor.api.ExtensionDef;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import tck.arquillian.porting.lib.spi.AbstractTestArchiveProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

public class JBossXmlProcessor extends AbstractTestArchiveProcessor {
    static Logger log = Logger.getLogger(JBossXmlProcessor.class.getName());
    static HashSet<String> sunXmlFiles = new HashSet<String>();
    static {
        sunXmlFiles.add("META-INF/sun-application-client.xml");
        sunXmlFiles.add("META-INF/sun-application.xml");
        sunXmlFiles.add("META-INF/sun-ra.xml");
        sunXmlFiles.add("WEB-INF/sun-web.xml");
        sunXmlFiles.add("META-INF/sun-ejb-jar.xml");
    }

    private Path descriptorDirRoot;

    /**
     * Called on completion of the Arquillian configuration.
     */
    public void initalize(@Observes ArquillianDescriptor descriptor) {
        // Must call to setup the ResourceProvider
        super.initalize(descriptor);

        // Get the descriptor path
        ExtensionDef descriptorsDef = descriptor.extension("jboss-descriptors");
        String descriptorDir = descriptorsDef.getExtensionProperties().get("descriptorDir");
        if(descriptorDir == null) {
            String msg = "Specify the descriptorDir property in arquillian.xml as extension:\n"+
                    "<extension qualifier=\"jboss-descriptors\">\n" +
                    "        <property name=\"descriptorDir\">path-to-descriptors-dir</property>\n" +
                    "</extension>";
            throw new IllegalStateException(msg);
        }
        this.descriptorDirRoot = Paths.get(descriptorDir);
        if(!Files.exists(this.descriptorDirRoot)) {
            throw new RuntimeException("Descriptor directory does not exist: " + this.descriptorDirRoot);
        }
    }

    @Override
    public void processClientArchive(JavaArchive clientArchive, Class<?> testClass, URL sunXmlURL) {

    }

    @Override
    public void processWebArchive(WebArchive webArchive, Class<?> testClass, URL sunXmlURL) {

    }

    @Override
    public void processRarArchive(JavaArchive warArchive, Class<?> testClass, URL sunXmlURL) {

    }

    @Override
    public void processEarArchive(EnterpriseArchive earArchive, Class<?> testClass, URL sunXmlURL) {

    }

    @Override
    public void processEjbArchive(JavaArchive ejbArchive, Class<?> testClass, URL sunXmlURL) {
        String pkgName = testClass.getPackageName();
        Path pkgPath = Paths.get(pkgName.replace(".", "/"));
        Path descriptorDir = descriptorDirRoot.resolve(pkgPath);
        List<File> files = findJBossDescriptors(descriptorDir);
        if(!files.isEmpty()) {
            String archiveName = ejbArchive.getName();
            for (File f : files) {
                try {
                    URL url = f.toURL();
                    String name = f.getName();
                    if(name.startsWith(archiveName)) {
                        String target = name.substring(archiveName.length()+1);
                        ejbArchive.addAsManifestResource(url, target);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    protected List<File> findJBossDescriptors(Path pkgPath) {
        try {
            List<File> files = Files.walk(pkgPath, 1)
                    .map(Path::toFile)
                    .toList();
            return files;
        } catch (Exception e) {
        }
        return Collections.emptyList();
    }
}
