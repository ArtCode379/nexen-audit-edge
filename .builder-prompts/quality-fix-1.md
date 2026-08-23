Fix the Android project at /tmp/nexen-audit-edge so the failing quality-fix-1 step passes.

Use these orchestrator instructions: /home/codex-agent/codex-app-agent/AGENTS.md
Screen spec: /home/codex-agent/codex-app-agent/screens-service.md
Do not push to GitHub, do not update Asana, and do not send Slack.
Fix formatting failures by expanding the affected Kotlin code; do not suppress or bypass the formatting checks.

Recent failure log:
```text
=== QUALITY CHECK: /tmp/nexen-audit-edge ===

WARN: Only 1 commit(s) — final implementation commit may not exist yet
/home/codex-agent/codex-app-agent/quality-check.sh: line 39: [: 0
0: integer expression expected
  OK: Repository: 0
0 entries
  PLACEHOLDER-LIKE: app/src/main/res/drawable/onboarding_cloud.jpg (colors=30268, entropy=0.672322)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/service_analytics.jpg (colors=55091, entropy=0.673932)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/icon_source.png (colors=48352, entropy=0.526621)
  OK: 10 images
  OK: All images valid
FAIL: 3 placeholder-like drawable image(s); use real photos or filesystem-backed imagegen output, not local generated placeholders
  OK: No empty onClick
  OK: No obvious no-op onClick handlers
  OK: icon.png (207360B, 512x512, rounded opaque canvas, transparent corners)
  OK: Application class ServiceApplication exists
  OK: HomeScreen.kt: 137 lines
  OK: No project-local agent instruction files
  OK: dynamicColor not enabled
  OK: Google Fonts dependency found
FAIL: font_certs.xml missing
  OK: HorizontalPager used
  OK: No drawable resources detected in AsyncImage lines
  OK: Kotlin source formatting

=== RESULT: 2 error(s) ===
FIX ALL ISSUES BEFORE PUSH

```
