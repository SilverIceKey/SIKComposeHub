# SIKComposeHub
### 集成方式：

在项目的setting.gradle或者root下的build.gradle中找到

```groovy
dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```

在app的build.gradle中进行依赖,版本：[![](https://jitpack.io/v/SilverIceKey/SIKComposeHub.svg)](https://jitpack.io/#SilverIceKey/SIKComposeHub)所有模块版本相同

```groovy
//全模块集成
dependencies {
	implementation("com.github.SilverIceKey:SIKComposeHub:Tag")
}
//单模块集成
dependencies {
    implementation("com.github.SilverIceKey.SIKComposeHub:Module:Tag")
}
```

### 项目介绍：

本项目是Android Compose的开发中台，配置常用的颜色什么的用于开发使用的，目前处于飞舞状态

### 项目包含以下元素：

- #### 缝合怪
- #### 屎山代码
- #### 高耦合
- #### 拎不清代码一堆

