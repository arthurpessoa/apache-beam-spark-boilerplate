package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ApacheLibraryAccessors laccForApacheLibraryAccessors = new ApacheLibraryAccessors(owner);
    private final AwaitilityLibraryAccessors laccForAwaitilityLibraryAccessors = new AwaitilityLibraryAccessors(owner);
    private final KafkaLibraryAccessors laccForKafkaLibraryAccessors = new KafkaLibraryAccessors(owner);
    private final KotlinLibraryAccessors laccForKotlinLibraryAccessors = new KotlinLibraryAccessors(owner);
    private final TestcontainersLibraryAccessors laccForTestcontainersLibraryAccessors = new TestcontainersLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for junit (org.junit.jupiter:junit-jupiter-engine)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() {
            return create("junit");
    }

    /**
     * Returns the group of libraries at apache
     */
    public ApacheLibraryAccessors getApache() {
        return laccForApacheLibraryAccessors;
    }

    /**
     * Returns the group of libraries at awaitility
     */
    public AwaitilityLibraryAccessors getAwaitility() {
        return laccForAwaitilityLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kafka
     */
    public KafkaLibraryAccessors getKafka() {
        return laccForKafkaLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlin
     */
    public KotlinLibraryAccessors getKotlin() {
        return laccForKotlinLibraryAccessors;
    }

    /**
     * Returns the group of libraries at testcontainers
     */
    public TestcontainersLibraryAccessors getTestcontainers() {
        return laccForTestcontainersLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class ApacheLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamLibraryAccessors laccForApacheBeamLibraryAccessors = new ApacheBeamLibraryAccessors(owner);

        public ApacheLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at apache.beam
         */
        public ApacheBeamLibraryAccessors getBeam() {
            return laccForApacheBeamLibraryAccessors;
        }

    }

    public static class ApacheBeamLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamRunnersLibraryAccessors laccForApacheBeamRunnersLibraryAccessors = new ApacheBeamRunnersLibraryAccessors(owner);
        private final ApacheBeamSdksLibraryAccessors laccForApacheBeamSdksLibraryAccessors = new ApacheBeamSdksLibraryAccessors(owner);

        public ApacheBeamLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for kafka (org.apache.beam:beam-sdks-java-io-kafka)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKafka() {
                return create("apache.beam.kafka");
        }

        /**
         * Returns the group of libraries at apache.beam.runners
         */
        public ApacheBeamRunnersLibraryAccessors getRunners() {
            return laccForApacheBeamRunnersLibraryAccessors;
        }

        /**
         * Returns the group of libraries at apache.beam.sdks
         */
        public ApacheBeamSdksLibraryAccessors getSdks() {
            return laccForApacheBeamSdksLibraryAccessors;
        }

    }

    public static class ApacheBeamRunnersLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamRunnersDirectLibraryAccessors laccForApacheBeamRunnersDirectLibraryAccessors = new ApacheBeamRunnersDirectLibraryAccessors(owner);

        public ApacheBeamRunnersLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for spark (org.apache.beam:beam-runners-spark-3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSpark() {
                return create("apache.beam.runners.spark");
        }

        /**
         * Returns the group of libraries at apache.beam.runners.direct
         */
        public ApacheBeamRunnersDirectLibraryAccessors getDirect() {
            return laccForApacheBeamRunnersDirectLibraryAccessors;
        }

    }

    public static class ApacheBeamRunnersDirectLibraryAccessors extends SubDependencyFactory {

        public ApacheBeamRunnersDirectLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for java (org.apache.beam:beam-runners-direct-java)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJava() {
                return create("apache.beam.runners.direct.java");
        }

    }

    public static class ApacheBeamSdksLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamSdksJavaLibraryAccessors laccForApacheBeamSdksJavaLibraryAccessors = new ApacheBeamSdksJavaLibraryAccessors(owner);

        public ApacheBeamSdksLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at apache.beam.sdks.java
         */
        public ApacheBeamSdksJavaLibraryAccessors getJava() {
            return laccForApacheBeamSdksJavaLibraryAccessors;
        }

    }

    public static class ApacheBeamSdksJavaLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamSdksJavaIoLibraryAccessors laccForApacheBeamSdksJavaIoLibraryAccessors = new ApacheBeamSdksJavaIoLibraryAccessors(owner);

        public ApacheBeamSdksJavaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.apache.beam:beam-sdks-java-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() {
                return create("apache.beam.sdks.java.core");
        }

        /**
         * Returns the group of libraries at apache.beam.sdks.java.io
         */
        public ApacheBeamSdksJavaIoLibraryAccessors getIo() {
            return laccForApacheBeamSdksJavaIoLibraryAccessors;
        }

    }

    public static class ApacheBeamSdksJavaIoLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamSdksJavaIoAmazonLibraryAccessors laccForApacheBeamSdksJavaIoAmazonLibraryAccessors = new ApacheBeamSdksJavaIoAmazonLibraryAccessors(owner);

        public ApacheBeamSdksJavaIoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at apache.beam.sdks.java.io.amazon
         */
        public ApacheBeamSdksJavaIoAmazonLibraryAccessors getAmazon() {
            return laccForApacheBeamSdksJavaIoAmazonLibraryAccessors;
        }

    }

    public static class ApacheBeamSdksJavaIoAmazonLibraryAccessors extends SubDependencyFactory {
        private final ApacheBeamSdksJavaIoAmazonWebLibraryAccessors laccForApacheBeamSdksJavaIoAmazonWebLibraryAccessors = new ApacheBeamSdksJavaIoAmazonWebLibraryAccessors(owner);

        public ApacheBeamSdksJavaIoAmazonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at apache.beam.sdks.java.io.amazon.web
         */
        public ApacheBeamSdksJavaIoAmazonWebLibraryAccessors getWeb() {
            return laccForApacheBeamSdksJavaIoAmazonWebLibraryAccessors;
        }

    }

    public static class ApacheBeamSdksJavaIoAmazonWebLibraryAccessors extends SubDependencyFactory {

        public ApacheBeamSdksJavaIoAmazonWebLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for services (org.apache.beam:beam-sdks-java-io-amazon-web-services)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getServices() {
                return create("apache.beam.sdks.java.io.amazon.web.services");
        }

    }

    public static class AwaitilityLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AwaitilityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for awaitility (org.awaitility:awaitility)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("awaitility");
        }

            /**
             * Creates a dependency provider for kotlin (org.awaitility:awaitility-kotlin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKotlin() {
                return create("awaitility.kotlin");
        }

    }

    public static class KafkaLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public KafkaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for kafka (org.apache.kafka:kafka_2.13)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() {
                return create("kafka");
        }

            /**
             * Creates a dependency provider for clients (org.apache.kafka:kafka-clients)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getClients() {
                return create("kafka.clients");
        }

    }

    public static class KotlinLibraryAccessors extends SubDependencyFactory {

        public KotlinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (org.jetbrains.kotlin:kotlin-test-junit5)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() {
                return create("kotlin.junit");
        }

    }

    public static class TestcontainersLibraryAccessors extends SubDependencyFactory {
        private final TestcontainersJunitLibraryAccessors laccForTestcontainersJunitLibraryAccessors = new TestcontainersJunitLibraryAccessors(owner);
        private final TestcontainersOracleLibraryAccessors laccForTestcontainersOracleLibraryAccessors = new TestcontainersOracleLibraryAccessors(owner);

        public TestcontainersLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for cassandra (org.testcontainers:cassandra)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCassandra() {
                return create("testcontainers.cassandra");
        }

            /**
             * Creates a dependency provider for kafka (org.testcontainers:kafka)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKafka() {
                return create("testcontainers.kafka");
        }

            /**
             * Creates a dependency provider for localstack (org.testcontainers:localstack)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getLocalstack() {
                return create("testcontainers.localstack");
        }

        /**
         * Returns the group of libraries at testcontainers.junit
         */
        public TestcontainersJunitLibraryAccessors getJunit() {
            return laccForTestcontainersJunitLibraryAccessors;
        }

        /**
         * Returns the group of libraries at testcontainers.oracle
         */
        public TestcontainersOracleLibraryAccessors getOracle() {
            return laccForTestcontainersOracleLibraryAccessors;
        }

    }

    public static class TestcontainersJunitLibraryAccessors extends SubDependencyFactory {

        public TestcontainersJunitLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for jupiter (org.testcontainers:junit-jupiter)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJupiter() {
                return create("testcontainers.junit.jupiter");
        }

    }

    public static class TestcontainersOracleLibraryAccessors extends SubDependencyFactory {

        public TestcontainersOracleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for xe (org.testcontainers:oracle-xe)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getXe() {
                return create("testcontainers.oracle.xe");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ApacheVersionAccessors vaccForApacheVersionAccessors = new ApacheVersionAccessors(providers, config);
        private final KotlinVersionAccessors vaccForKotlinVersionAccessors = new KotlinVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: awaitility (4.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAwaitility() { return getVersion("awaitility"); }

            /**
             * Returns the version associated to this alias: junit (5.9.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: kafka (3.4.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKafka() { return getVersion("kafka"); }

            /**
             * Returns the version associated to this alias: shadow (8.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getShadow() { return getVersion("shadow"); }

            /**
             * Returns the version associated to this alias: testcontainers (1.19.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestcontainers() { return getVersion("testcontainers"); }

        /**
         * Returns the group of versions at versions.apache
         */
        public ApacheVersionAccessors getApache() {
            return vaccForApacheVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.kotlin
         */
        public KotlinVersionAccessors getKotlin() {
            return vaccForKotlinVersionAccessors;
        }

    }

    public static class ApacheVersionAccessors extends VersionFactory  {

        public ApacheVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: apache.beam (2.51.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBeam() { return getVersion("apache.beam"); }

    }

    public static class KotlinVersionAccessors extends VersionFactory  implements VersionNotationSupplier {

        public KotlinVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the version associated to this alias: kotlin (1.9.10)
         * If the version is a rich version and that its not expressible as a
         * single version string, then an empty string is returned.
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> asProvider() { return getVersion("kotlin"); }

            /**
             * Returns the version associated to this alias: kotlin.junit (1.8.10)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("kotlin.junit"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for awaitility which is an aggregate for the following dependencies:
             * <ul>
             *    <li>org.awaitility:awaitility-kotlin</li>
             *    <li>org.awaitility:awaitility</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getAwaitility() {
                return createBundle("awaitility");
            }

    }

    public static class PluginAccessors extends PluginFactory {
        private final KotlinPluginAccessors paccForKotlinPluginAccessors = new KotlinPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for shadow to the plugin id 'com.github.johnrengelman.shadow'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getShadow() { return createPlugin("shadow"); }

        /**
         * Returns the group of plugins at plugins.kotlin
         */
        public KotlinPluginAccessors getKotlin() {
            return paccForKotlinPluginAccessors;
        }

    }

    public static class KotlinPluginAccessors extends PluginFactory {

        public KotlinPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for kotlin.allopen to the plugin id 'org.jetbrains.kotlin.plugin.allopen'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAllopen() { return createPlugin("kotlin.allopen"); }

            /**
             * Creates a plugin provider for kotlin.jvm to the plugin id 'org.jetbrains.kotlin.jvm'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getJvm() { return createPlugin("kotlin.jvm"); }

    }

}
