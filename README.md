# Cleanroom Example Mod

このテンプレートは現時点では使用できません。

Cleanroom0.7.0に向けてCleanroomGradle, Cleanroom Versioning, TokenEnvoyを使えるModTemplateを開発しようとした(?)
Javaパッケージの既定のルートは `com.sumirelabs`

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
