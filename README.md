# rmspe-weka-package

Weka package that adds the RMSPE (Root Mean Square Percentage Error) as metric 
for classifiers (numeric classes only). 

For formula, see [here](https://www.kaggle.com/c/rossmann-store-sales/overview/evaluation).


## Releases

* [2021.3.12](https://github.com/fracpete/rmspe-weka-package/releases/download/v2021.3.12/rmspe-2021.3.12.zip)



## How to use packages

For more information on how to install the package, see:

https://waikato.github.io/weka-wiki/packages/manager/


## Maven

Add the following dependency in your `pom.xml` to include the package.

The following dependency automatically pulls in Weka:

```xml
    <dependency>
      <groupId>com.github.fracpete</groupId>
      <artifactId>rmspe-weka-package</artifactId>
      <version>2021.3.12</version>
    </dependency>
```

Use the following dependency to exclude the Weka dependencies:

```xml
    <dependency>
      <groupId>com.github.fracpete</groupId>
      <artifactId>rmspe-weka-package</artifactId>
      <version>2021.3.12</version>
      <exclusions>
        <exclusion>
          <groupId>nz.ac.waikato.cms.weka</groupId>
          <artifactId>weka-dev</artifactId>
        </exclusion>
        <exclusion>
          <groupId>org.pentaho.pentaho-commons</groupId>
          <artifactId>pentaho-package-manager</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
```

