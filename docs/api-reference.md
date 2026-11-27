# Android-to-Groovy API contract

This document defines the host API that the Android app must expose. These are contract names, not a claim that the current APK already implements them.

## Global objects

- `app`: package/activity inspection and launch.
- `ui`: accessibility snapshots, node queries, click and scroll.
- `device`: back navigation and cancellable foreground waits.
- `debug`: hierarchy and screenshot export.
- `log`: structured logging.

## Required methods

### app
- `app.currentPackage()` -> String
- `app.currentActivity()` -> String
- `app.launch(packageName)` -> boolean

### ui
- `ui.snapshot()` -> immutable snapshot with `textList()`, `hasAll(labels)`, `findText(text, exact: true)`, `findAll(text)`
- Node fields: `text`, `contentDescription`, `viewId`, `className`, `bounds`, `clickable`, `enabled`, `visible`, `depth`
- Node methods: `click()`, `clickParent()`, `parent()`, `children()`
- `ui.scroll(direction, container)` -> boolean
- A text match is never sufficient by itself for a click. Scripts must require a unique, visible, enabled node and use its clickable ancestor where needed.

### device
- `device.back()` -> boolean
- `device.waitForeground(packageName, durationMs)` -> cancellable wait result
- `device.wait(durationMs)` -> cancellable wait result

### debug
- `debug.dumpTree(label)`
- `debug.screenshot(label)`

### log
- `log.info(message)`
- `log.warn(message)`
- `log.error(message)`

## Safety invariants
1. Verify package and page before every action.
2. Never scroll unless the expected task-list page is confirmed.
3. Take a fresh snapshot after every click, back action, or scroll.
4. Do not treat a WebView as an external app solely because its content changed.
5. Do not skip actual foreground viewing/listening time or fake completion.
6. If a match is ambiguous, export diagnostics and stop.
