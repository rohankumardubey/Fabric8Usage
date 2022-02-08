import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Sample {
    private static final Logger logger = LoggerFactory.getLogger(Sample.class);

    public static void main(String[] args) {
//        String master = "http://127.0.0.1:61590/";

//        Config config = new ConfigBuilder().withMasterUrl(master).build();
//        try (final KubernetesClient client = new DefaultKubernetesClient()) {
//            // Simple Listing:
//          client.services().inNamespace("default")
//                          .list().getItems().forEach(service -> System.out.println(service.getMetadata().getName()));
//
//            System.out.println("===================");
//            client.pods().inNamespace("default").list().getItems().forEach(
//                    pod -> System.out.println(pod.getMetadata().getName())
//            );
//            System.out.println("===================");
////            client.namespaces()
////                    .list()
////                    .getItems()
////                    .stream()
////                    .map(Namespace::getMetadata)
////                    .map(ObjectMeta::getName)
////                    .forEach(logger::info);
////            // List with limit and continue options:
//
//        }
//
        Config config = new ConfigBuilder().withMasterUrl("https://10.4.17.40:51999/").build();
//        KubernetesClient client = new DefaultKubernetesClient(config);
        try (KubernetesClient client = new DefaultKubernetesClient(config)) {
            List<Service> services = client.services().inNamespace("default")
                    .list()
                    .getItems();
            for (Service service : services) {
                System.out.println(service.getMetadata().getNamespace());
                System.out.println(service.getMetadata().getName());
                System.out.println(service.getMetadata().getCreationTimestamp());
                System.out.println(service.getSpec().getClusterIP());
                System.out.println(service.getSpec().getPorts());
                System.out.println("==========================================");
            }

        }
        catch (KubernetesClientException e) {
            e.printStackTrace();
        }
    }
}
