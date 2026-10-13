# Cleanroom Example Mod

Cleanroom 0.7.2-alpha向けのJava専用Modテンプレートです。
CleanroomGradle 0.22.0、Cleanroom Versioning、TokenEnvoy、Foojayを使用します。
Javaパッケージの既定のルートは `com.sumirelabs`

## 開発環境とビルド

Java 25 JDKをGradleの実行JVMに設定し、Gradle Wrapperを使用してください。
Foojayはコンパイル・ゲーム起動用toolchainを解決します。
公開済みの`com.cleanroommc:cleanroom-userdev:0.7.2-alpha`を使うため、
Cleanroom本体のローカルビルドやMaven Localの有効化は不要です。
LoaderのMaven座標にも`-alpha`が含まれます。旧`0.7.0`座標は利用できません。
これはLoader依存物のバージョンであり、このModのGitバージョン管理とは別です。

```powershell
./gradlew.bat clean build
./gradlew.bat runClient
./gradlew.bat runServer
```

Linux/macOSでは`./gradlew`を使います。IntelliJではGradle JVMにJava 25を選択してください。
サーバーの初回起動にはMinecraft EULAへの同意が必要です。
初回はuserdevのダウンロードと変換に時間がかかります。
配布用jarは`build/libs/<mod_id>-<version>.jar`、開発用jarは`*-mcp.jar`です。
`check`と`build`は`verifyModJar`で配布用メタデータ・refmap・Mixin無効時の除外も検査します。

## Mixinと任意機能

Mixinは標準ONで、通常・Mixin構成を単一ブランチにまとめています。

```powershell
./gradlew.bat clean build -Penable_mixin=false
```

`src/mixin/java`とMixin設定は無効構成から除外されます。
CleanroomGradleの`cg.userdev.refmap`でrefmapを生成し、processorのバージョンはLoaderに合わせて自動選択します。
追加のメンバーマッピングを統合する処理を残し、`@Shadow`などの配布時の名前変換にも対応します。
開発起動でもrefmapを有効にし、CleanMixの開発用remapperを使います。
開発起動には公式移行版と同じ`foundation.loadsall=true`を設定し、
Foundation 0.22.0でのMod/注釈クラスのクラスローダー不一致を回避しています。

Mod ID・パッケージを変更する場合はJavaの配置・宣言と、`cg.userdev.refmap`の固有名も合わせて変更してください。
`use_access_transformer`、`is_coremod`、`enable_shadow`、`use_asset_mover`は個別に有効化できます。
ATは`src/main/resources/META-INF/<mod_id>_at.cfg`へMCP名で記述します。
ATを有効にして初めてIDEへ同期する際は`prepareAccessTransformer`を一度実行してください。
依存関係は`gradle/scripts/dependencies.gradle`、独自設定は`gradle/scripts/extra.gradle`へ記述します。
他ModのSRG jarには`deobf(...)`、依存jarの同梱には`contain`、Shadow対象には`shade`を使います。
CleanMix/MixinExtrasのコンパイル用バージョンは`gradle.properties`でLoaderに合わせて指定します。
LWJGLYはuserdevが指定するバージョンを使用します。Loaderの更新は`cleanroom_version`で追従します。
テンプレート側からLWJGLYの強制差し替えやLoaderの初期化処理へのパッチは行っていません。

## バージョン管理

Cleanroom Versioning 3.2.0でGitタグからバージョンを計算します。
`mod_version`や`version`を手動設定しません。リリースブランチは`main`です。

```powershell
./gradlew.bat -q printVersion
```

例えばタグ`0.1.0`上では`0.1.0`、その後の`main`での開発は
`0.1.1-dev.<コミット数>.local`などになります。未コミットの変更には`.dirty`が付きます。
タグがまだない場合はGit履歴を基準に`0.0.1`から計算するため、
従来の`mod_version=0.1.0`がそのまま引き継がれるわけではありません。

正式なリリース番号は`0.1.0`や`v0.1.0`のタグで指定します。
major/minor/patchの選択は手動です。タグの作成・pushはリリースするときに行ってください。
公開済み3.2.0ではstage付きタグ（`0.1.0-alpha`など）は使いません。
公開段階は`versioning.stage = alpha`などで別に設定します。
未設定なら1.0.0未満はbeta、1.0.0以上はreleaseです。

計算した番号をjar名、Maven、`Reference.VERSION`、`@Mod`、`mcmod.info`に使います。
Actionsは履歴・タグを取得し、公開時のバージョンと公開段階も同じ計算結果から読み取ります。
CurseForge/Modrinthの手動公開では「Use workflow from」でバージョンタグを選択してください。
RCは両サイトのbetaとして扱います。開発ブランチからの公開は行いません。
## 出典

- [CleanroomModTemplate](https://github.com/CleanroomMC/CleanroomModTemplate)
- [CleanroomGradle](https://github.com/CleanroomMC/CleanroomGradle)
- [TokenEnvoy](https://github.com/CleanroomMC/TokenEnvoy)
- [Cleanroom](https://github.com/CleanroomMC/Cleanroom)
