---
name: Offgrid Tactical Mesh
colors:
  surface: '#0b1422'
  surface-dim: '#0b1422'
  surface-bright: '#313949'
  surface-container-lowest: '#060e1c'
  surface-container-low: '#131c2a'
  surface-container: '#17202e'
  surface-container-high: '#222a39'
  surface-container-highest: '#2c3544'
  on-surface: '#dae3f7'
  on-surface-variant: '#c3c5d8'
  inverse-surface: '#dae3f7'
  inverse-on-surface: '#283140'
  outline: '#8d90a2'
  outline-variant: '#434656'
  surface-tint: '#b6c4ff'
  primary: '#b6c4ff'
  on-primary: '#00287d'
  primary-container: '#2563ff'
  on-primary-container: '#f7f6ff'
  inverse-primary: '#004fe5'
  secondary: '#a2e7ff'
  on-secondary: '#003642'
  secondary-container: '#00d2fd'
  on-secondary-container: '#005669'
  tertiary: '#c9bfff'
  on-tertiary: '#2e009c'
  tertiary-container: '#7055f3'
  on-tertiary-container: '#faf5ff'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#dce1ff'
  primary-fixed-dim: '#b6c4ff'
  on-primary-fixed: '#00164f'
  on-primary-fixed-variant: '#003baf'
  secondary-fixed: '#b4ebff'
  secondary-fixed-dim: '#3cd7ff'
  on-secondary-fixed: '#001f27'
  on-secondary-fixed-variant: '#004e5f'
  tertiary-fixed: '#e5deff'
  tertiary-fixed-dim: '#c9bfff'
  on-tertiary-fixed: '#1a0063'
  on-tertiary-fixed-variant: '#441cc8'
  background: '#0b1422'
  on-background: '#dae3f7'
  surface-variant: '#2c3544'
typography:
  display-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 40px
    fontWeight: '800'
    lineHeight: 48px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.01em
  headline-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: -0.01em
  headline-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  title-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 18px
    fontWeight: '600'
    lineHeight: 24px
  title-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  body-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 16px
  label-lg:
    fontFamily: JetBrains Mono
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.02em
  label-md:
    fontFamily: JetBrains Mono
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.04em
  label-sm:
    fontFamily: JetBrains Mono
    fontSize: 10px
    fontWeight: '600'
    lineHeight: 14px
    letterSpacing: 0.08em
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  margin: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
---

## Brand & Style

This design system establishes a high-reliability, mission-critical interface engineered for decentralized off-grid emergency operations. The aesthetic merges Material 3 Expressive dynamism with hyper-legible tactical telemetry. It communicates absolute resilience, instantaneous situational awareness, and technological sophistication under extreme scenarios.

The design language balances dense situational telemetry with immediate touch targets for high-stress environments. Visual hierarchies rely on deep midnight backdrops paired with luminescent data accents, glassmorphic signal relays, and tactical node structures. Interfaces project confidence, extreme operational clarity, and precision without unnecessary decorative friction.

## Colors

The palette is engineered strictly for dark-mode-first tactical readiness, prioritizing screen efficiency, eye comfort in low-light environments, and high visual contrast for fast triage:

- **Surface Foundations:** Background anchors at Deep Midnight Navy (`#08111F`), with tiered container elevations resting on Tactical Slate (`#101B2D`) and elevated glass plates (`rgba(255, 255, 255, 0.08)`).
- **Core Signal Vectors:** Electric Blue (`#2563FF`) anchors primary actions and system pings. Neon Cyan (`#00D4FF`) highlights telemetry, mesh synchronization, and active data transfers. Violet (`#7B61FF`) governs auxiliary peer networks, frequency hopping, and cryptographic states.
- **Status & Mission Triage:**
  - **Emergency Crimson (`#FF3B5C`):** Dedicated to life safety alerts, SOS broadcast states, and hardware faults.
  - **Safety Amber (`#FF8A00`):** Indicates low bandwidth, degraded mesh health, environmental warnings, and packet drops.
  - **Success Emerald (`#1DB954`):** Confirms node sync, verified handshakes, and resilient signal relays.
- **Micro-Borders & Glows:** Frosted containment borders use `rgba(255, 255, 255, 0.12)`, scaling up to active signal glows utilizing `0 0 16px rgba(0, 212, 255, 0.45)`.

## Typography

Typography balances rapid scanning with technical precision. Headlines feature **Plus Jakarta Sans** for rounded, punchy authority under stressful conditions. Narrative flows and tactical dispatches leverage **Inter** for neutral legibility. System readouts, coordinates, peer IDs, and telemetry rely on **JetBrains Mono**.

Data labels and status tags use uppercase monospace configurations with distinct letter-spacing to prevent misinterpretation of numbers and characters (such as 0 vs O, 1 vs I) in critical operational alerts.

## Layout & Spacing

The layout is optimized for high-pressure mobile operation, utilizing a dynamic 4-column fluid mobile grid scaling to an 8-column layout on handheld tactical tablets. 

Touch targets are prioritized with a strict minimum footprint of 48px to accommodate gloves, moisture, and shaky hands. Horizontal outer margins default to `1rem` (16px) to maximize horizontal data visualization on narrow displays. Vertical spacing follows a rhythmic 4px base baseline, with 16px (`space-md`) serving as standard operational padding within containers and cards.

## Elevation & Depth

Depth is conveyed through luminous glassmorphism and ambient tactical glows rather than traditional opaque shadows:

- **Level 0 (Floor):** Midnight Navy `#08111F` canvas with occasional subtle radar concentric grid lines in `rgba(0, 212, 255, 0.04)`.
- **Level 1 (Card Baseline):** Tactical Slate `#101B2D` with an overlay of `rgba(255, 255, 255, 0.04)` and a 1px border of `rgba(255, 255, 255, 0.08)`.
- **Level 2 (Glass Tactical Surfaces):** `rgba(255, 255, 255, 0.08)` filled with a 16px backdrop blur (`backdrop-filter: blur(16px)`), outlined with a crisp top-lit border (`rgba(255, 255, 255, 0.16)` top, `rgba(255, 255, 255, 0.04)` bottom).
- **Level 3 (Interactive / Alert Elevation):** Floated overlays apply an ambient glow matched to status:
  - Emergency Broadcast: `0 8px 32px -4px rgba(255, 59, 92, 0.35)`
  - Active Peer Relay: `0 8px 32px -4px rgba(0, 212, 255, 0.30)`
  - Encrypted Mesh Beacon: `0 8px 32px -4px rgba(123, 97, 255, 0.25)`

## Shapes

The design system pairs structural roundedness (Level 2: 8px corners) with hyper-ergonomic full pill contours for interactive action modules.

- **Cards & Data Modules:** 16px corner radius (`rounded-lg`) provides container durability without sacrificing structural efficiency.
- **Buttons, Status Badges & Telemetry Chips:** Fully rounded 9999px pill shapes provide unmistakable affordance and tactile visual separation from rigid telemetry tiles.
- **Radar & Signal Reticles:** Perfect concentric circles with glowing crosshairs, reinforcing technical mesh infrastructure.

## Components

### Action Controls & Buttons
- **SOS Emergency Trigger:** Massive pill component in Emergency Crimson (`#FF3B5C`) with a continuous outer radar pulse animation (`rgba(255, 59, 92, 0.4)`). White bold text with tactile internal glow.
- **Primary Signal Action:** Electric Blue (`#2563FF`) background with a soft Neon Cyan highlight border and high-contrast white text.
- **Tactical Secondary:** Translucent Glass base (`rgba(255, 255, 255, 0.08)`) with a 1px solid border of Neon Cyan (`#00D4FF`) at 50% opacity, shifting to 100% on active tap.

### Pill Badges & Telemetry Chips
- Pill contours housing a leading 6px pulsing LED indicator dot.
- Connected status: Dark emerald glass fill with glowing Emerald text (`#1DB954`).
- Hop Count / Signal dBm: Dark navy fill, monospace typography (`label-sm`), with `rgba(0, 212, 255, 0.2)` border.

### Tactical Glass Cards
- Built with `rgba(255, 255, 255, 0.08)` fill and `backdrop-filter: blur(16px)`.
- Edge highlight: subtle gradient border fading from `rgba(255, 255, 255, 0.2)` down to `rgba(255, 255, 255, 0.03)`.
- Header area houses high-density monospace coordinates and mesh latency indicators.

### Inputs & Frequency Selectors
- Recessed slate background (`#0B1524`) with a 1px border of `rgba(255, 255, 255, 0.12)`.
- Active focus state initiates a Neon Cyan halo glow (`box-shadow: 0 0 0 2px #00D4FF`).
- Fixed monospaced unit suffixes (e.g., `MHz`, `dBm`, `KM`) anchored to trailing edges.

### Mesh Network & Radar Modules
- Visualized peer-to-peer link paths rendered in variable-opacity Neon Cyan vectors.
- Interactive nodes pulsate with radial ripples illustrating transmission range and packet relay health.