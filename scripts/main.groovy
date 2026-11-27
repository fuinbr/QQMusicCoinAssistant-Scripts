/*
 * Entry point for QQ Music coin-task automation.
 * Business decisions live in Groovy; Android provides only host capabilities.
 *
 * This scaffold intentionally performs inspection only until the Android host
 * exposes and validates the API contract in docs/api-reference.md.
 */

final String QQ_MUSIC = "com.tencent.qqmusic"

log.info("Groovy script started")

def packageName = app.currentPackage()
log.info("Foreground package: ${packageName}")

if (packageName != QQ_MUSIC) {
    log.info("QQ Music is not foreground; launching it")
    if (!app.launch(QQ_MUSIC)) {
        log.error("Unable to launch QQ Music")
        return
    }
    if (!device.waitForeground(QQ_MUSIC, 15000)) {
        log.error("QQ Music did not become foreground in time")
        debug.dumpTree("launch-timeout")
        debug.screenshot("launch-timeout")
        return
    }
}

def page = ui.snapshot()
log.info("Initial accessibility snapshot captured")

if (page == null) {
    log.error("No active accessibility root; stopping safely")
    debug.dumpTree("empty-snapshot")
    debug.screenshot("empty-snapshot")
    return
}

log.info("Visible labels: " + page.textList().take(80).join(" | "))
debug.dumpTree("initial-page")

// Navigation and task state machine will be added here once the Android
// bridge has passed device-level tests for snapshot, exact-node matching,
// clickable-ancestor resolution, and fresh-snapshot semantics.
log.info("Inspection-only scaffold finished; no task was clicked")
