---
name: OFFGRID
colors:
  surface: '#111827'
  surface-dim: '#11131c'
  surface-bright: '#373943'
  surface-container-lowest: '#0c0e16'
  surface-container-low: '#191b24'
  surface-container: '#1d1f28'
  surface-container-high: '#282a33'
  surface-container-highest: '#32343e'
  on-surface: '#e1e1ee'
  on-surface-variant: '#c3c5d8'
  inverse-surface: '#e1e1ee'
  inverse-on-surface: '#2e303a'
  outline: '#8d90a2'
  outline-variant: '#434656'
  surface-tint: '#b6c4ff'
  primary: '#b6c4ff'
  on-primary: '#00287d'
  primary-container: '#2563ff'
  on-primary-container: '#f7f6ff'
  inverse-primary: '#004fe5'
  secondary: '#d2bbff'
  on-secondary: '#3f008e'
  secondary-container: '#6001d1'
  on-secondary-container: '#c9aeff'
  tertiary: '#ffb59a'
  on-tertiary: '#5b1b00'
  tertiary-container: '#c84500'
  on-tertiary-container: '#fff5f2'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#dce1ff'
  primary-fixed-dim: '#b6c4ff'
  on-primary-fixed: '#00164f'
  on-primary-fixed-variant: '#003baf'
  secondary-fixed: '#eaddff'
  secondary-fixed-dim: '#d2bbff'
  on-secondary-fixed: '#25005a'
  on-secondary-fixed-variant: '#5a00c6'
  tertiary-fixed: '#ffdbcf'
  tertiary-fixed-dim: '#ffb59a'
  on-tertiary-fixed: '#380d00'
  on-tertiary-fixed-variant: '#802900'
  background: '#11131c'
  on-background: '#e1e1ee'
  surface-variant: '#32343e'
  primary-background: '#050816'
  secondary-background: '#0D1524'
  glass-surface: rgba(255,255,255,0.08)
  glass-border: rgba(255,255,255,0.15)
  success: '#1DB954'
  warning: '#F59E0B'
  danger: '#DC2626'
  ai-accent: '#8B5CF6'
  location-accent: '#14B8A6'
  mesh-accent: '#22D3EE'
  bluetooth-accent: '#3B82F6'
  text-primary: '#FFFFFF'
  text-secondary: '#CBD5E1'
  text-muted: '#64748B'
  divider: rgba(255,255,255,0.08)
typography:
  display-large:
    fontFamily: Google Sans
    fontSize: 40sp
    fontWeight: '700'
    lineHeight: 48sp
  display-medium:
    fontFamily: Google Sans
    fontSize: 32sp
    fontWeight: '700'
    lineHeight: 40sp
  headline-lg:
    fontFamily: Google Sans
    fontSize: 28sp
    fontWeight: '600'
    lineHeight: 36sp
  title-large:
    fontFamily: Google Sans
    fontSize: 24sp
    fontWeight: '600'
    lineHeight: 32sp
  title-medium:
    fontFamily: Google Sans
    fontSize: 20sp
    fontWeight: '500'
    lineHeight: 28sp
  body-large:
    fontFamily: Inter
    fontSize: 16sp
    fontWeight: '400'
    lineHeight: 24sp
  body-medium:
    fontFamily: Inter
    fontSize: 15sp
    fontWeight: '400'
    lineHeight: 22sp
  caption:
    fontFamily: Inter
    fontSize: 13sp
    fontWeight: '400'
    lineHeight: 18sp
  button:
    fontFamily: Inter
    fontSize: 15sp
    fontWeight: '600'
    lineHeight: 20sp
  mono-data:
    fontFamily: JetBrains Mono
    fontSize: 13sp
    fontWeight: '500'
    lineHeight: 18sp
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 16dp
  margin: 24dp
  space-xs: 4dp
  space-sm: 8dp
  space-md: 16dp
  space-lg: 24dp
  space-xl: 32dp
---

## Brand & Style

This design system combines Google's Material 3 Expressive principles with Apple's fluid animation models and Nothing OS minimalism. The visual narrative is built for high-stakes offline emergency communications, projecting absolute reliability, futuristic intelligence, and life-saving urgency.

- **Brand Personality:** Trustworthy, futuristic, intelligent, fast, and life-saving.
- **Target Audience:** Field operators, emergency responders, travelers, and individuals in disconnected or disaster-stricken environments.
- **Design Style:** Glassmorphism mixed with high-contrast tactical elements, ambient glowing surfaces, fluid spring physics, and dynamic micro-interactions.
- **Emotional Response:** Reassurance in crisis, absolute situational awareness, and calm technological empowerment.

## Colors

The color system is optimized for high legibility in low-light emergency scenarios, utilizing a deep navy digital canvas layered with vibrant neon cyan, electric blue, and urgent emergency hues.

- **Backgrounds:** Immersive deep dark navy (`#050816`) layered with elevated structural panels (`#0D1524`, `#111827`).
- **Glass Layers:** Translucent white overlays (`rgba(255,255,255,0.08)`) with subtle luminous boundaries (`rgba(255,255,255,0.15)`).
- **Functional Accents:** Dedicated chromatic roles for AI intelligence, mesh topology, Bluetooth signals, and life-critical SOS triggers.

## Typography

Typography establishes an authoritative, clean hierarchy designed for rapid scanning under high-stress conditions.

- **Primary Headline Font:** Google Sans provides geometric clarity and bold presence for critical titles and large displays.
- **Body Font:** Inter ensures exceptional legibility across dense tactical logs, maps, and chat interfaces.
- **Monospace Font:** JetBrains Mono is reserved for telemetry, coordinates, mesh packet hashes, and system metrics.

## Layout & Spacing

The layout is built on a rigid 8dp spatial grid combined with fluid responsive scaling to guarantee ergonomic target execution across modern Android form factors.

- **Grid Model:** Fluid grid configuration featuring 24dp horizontal safe margins and 16dp structural gutters.
- **Responsive Adaptations:** Layout scales smoothly across standard mobile viewports (360px to 428px widths) while maintaining strict touch targets (minimum 48dp).
- **Padding Hierarchy:** Consistent spacing scales (`space-xs` through `space-xl`) govern internal component padding and structural stack gaps.

## Elevation & Depth

Visual hierarchy is communicated through luminous depth layers and frosted glass characteristics rather than traditional heavy drop shadows.

- **Glassmorphic Surfaces:** Hero cards and functional panels utilize backdrop blurs, 12% translucent white fills, and gradient borders to create floating, tactile tiers.
- **Ambient Glows:** Active network states, SOS triggers, and AI assistants emit soft colored blooms (blue, purple, red, cyan) to indicate live operational status.
- **Floating Elevation:** Interactive elements lift dynamically on press, utilizing low-opacity, high-blur ambient shadows to separate layers from the dark navy background.

## Shapes

The shape language utilizes organic, rounded geometries that soften the tactical nature of emergency equipment while preserving ergonomic efficiency.

- **Containers & Cards:** Generous radii (`28dp` to `32dp` for bottom sheets and hero cards) produce friendly, modern touchpoints.
- **Interactive Controls:** Buttons and inputs feature distinct pill and capsule curvatures (`18dp` to `20dp`) with full circular (`100%`) floating action buttons.
- **Icons & Badges:** Contained within structured `16dp` rounded containers to maintain visual consistency across dense dashboards.

## Components

Every component is engineered for tactile feedback, immediate state comprehension, and high-performance mobile execution.

- **Buttons:** Primary buttons feature gradient fills, pill shapes, and press glows. Emergency buttons are oversized (120dp) with continuous breathing pulses and countdown rings.
- **Cards:** Hero cards incorporate background noise textures, live gradient borders, and embedded metrics. Device cards feature collapsible signal strengths, battery metrics, and direct mesh connection triggers.
- **Input Fields & Search Bars:** Rounded glass capsules featuring floating labels, animated focus borders, voice input triggers, and contextual action buttons.
- **Chips:** Highly versatile status, filter, and language toggles with active gradient fill states and smooth selection transitions.
- **Navigation:** Fixed glass bottom dock with five core destinations and animated indicator pills. Top app bars feature sticky blur-on-scroll behaviors with integrated status badges and profile avatars.
- **AI & Emergency Feed:** Glass-rendered cards with subtle purple glows, streaming typing animations, and live waveform feedback during voice interactions.