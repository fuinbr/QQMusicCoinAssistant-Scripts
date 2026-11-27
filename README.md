# QQMusicCoinAssistant Scripts

Independent Groovy scripts for the native Android QQ Music Coin Assistant.

## Channels
- `stable`: scripts intended for normal use.
- `dev`: scripts under active development.

## Runtime contract
Scripts run inside the Android app's embedded Groovy runtime. They must use only the host APIs documented in `docs/api-reference.md`; Android framework objects are not exposed directly.

## Safety
- Do not fabricate video, ad, listening, or browsing time.
- Do not bypass human verification or platform restrictions.
- Confirm the current page before any click or scroll.
- Re-snapshot the accessibility tree after every navigation or UI mutation.
- On an ambiguous match, log diagnostics and stop rather than guessing.

## Development
Edit scripts in this repository. The Android app checks `manifest.json`, downloads the selected channel, verifies SHA-256 hashes, and caches the last known-good version locally.
