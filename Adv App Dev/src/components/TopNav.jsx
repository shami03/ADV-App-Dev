import React from 'react'

function TopNav() {
  return (
    <div>
    <header className="site-header">
    <nav>
      <a href="/"><strong>AgriFunding</strong></a> | {/* Change "JR" to "AgriFunding" */}
      <a href="/">Projects</a> {/* Change "Blog" to "Projects" */}
      <a href="/">Investors</a> {/* Change "Books" to "Investors" */}
      <a href="/">About</a> {/* Change "Info" to "About" */}
      <a href="/">Contact</a> {/* Change "Newsletter" to "Contact" */}
    </nav>
  </header>
  </div>
    )
}

export default TopNav