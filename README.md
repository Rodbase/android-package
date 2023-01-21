# Rodbase Android

- Used with [codeigniter 3](https://github.com/bcit-ci/CodeIgniter)
- if you are familiar with [codeigniter](https://github.com/bcit-ci/CodeIgniter) 3 it will be easier to use

## Contents

- [Requirements](#requirements)
- [Installation](#installation)
    - [gradle](#gradle)
    - [maven](#maven)
    - [sbt](#sbt)
    - [leiningen](#leiningen)

## Requirements

- Domain/Hosting supports php > 7.3.+ and one of this databases:
  - MySQL (5.1+) via the mysql (deprecated), mysqli and pdo drivers
  - Oracle via the oci8 and pdo drivers
  - PostgreSQL via the postgre and pdo drivers
  - MS SQL via the mssql, sqlsrv (version 2005 and above only) and pdo drivers
  - SQLite via the sqlite (version 2), sqlite3 (version 3) and pdo drivers
  - CUBRID via the cubrid and pdo drivers
  - Interbase/Firebird via the ibase and pdo drivers
  - ODBC via the odbc and pdo drivers (you should know that ODBC is actually an abstraction layer)
  - See [Rodbase Rest Api for PHP](https://github.com/Rodbase/php-rest-api) for how to set database.
- Download this repo and upload files to any path in your ftp: [Rodbase Rest Api for PHP](https://github.com/Rodbase/php-rest-api)
- Follow the instructions in [Rodbase Rest Api for PHP](https://github.com/Rodbase/php-rest-api)

[Go Top](#rodbase-android)

## Installation

[![](https://jitpack.io/v/Rodbase/android-package.svg)](https://jitpack.io/#Rodbase/android-package)

### gradle

1. Add the JitPack repository to your build file
    - Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Or (if you don't have allprojects block in build.gradle).
- Add it in your settings.gradle:

```
dependencyResolutionManagement {
    ...
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency
```
dependencies {
    implementation 'com.github.Rodbase:android-package:0.9.5'
}
```

### maven

1. Add the JitPack repository to your build file
```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

2. Add the dependency
```
<dependency>
    <groupId>com.github.Rodbase</groupId>
    <artifactId>android-package</artifactId>
    <version>0.9.5</version>
</dependency>
```

### sbt

1. Add the JitPack repository to your build file
    - Add it in your build.sbt at the end of resolvers:
```
resolvers += "jitpack" at "https://jitpack.io"
```

2. Add the dependency
```
libraryDependencies += "com.github.Rodbase" % "android-package" % "0.9.5"	
```

### leiningen
1. Add the JitPack repository to your build file
    - Add it in your project.clj at the end of repositories:
```
:repositories [["jitpack" "https://jitpack.io"]]
```

2. Add the dependency
```
:dependencies [[com.github.Rodbase/android-package "0.9.5"]]	
```

[Go Top](#rodbase-android)