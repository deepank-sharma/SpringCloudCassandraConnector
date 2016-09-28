User-Service

Has an implementation for creating Cassandra Service Connector based on profiles[Dev vs Cloud].
It reads the VCAP variables from Pivotal Cloud Foundry and creates Cassandra Connection if deployed on cloud or else creates a connection with the local version of Cassandra if deployed on local.
