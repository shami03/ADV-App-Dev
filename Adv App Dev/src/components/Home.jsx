import React from 'react';
import './Home.css'; // Assuming you have styles defined in this file

function Home() {
  return (
    <div className="wrapper">
      <header className="site-header">
        <nav>
          <a href="/"><strong>AgriFunding</strong></a> | {/* Change "JR" to "AgriFunding" */}
          <a href="/">Projects</a> {/* Change "Blog" to "Projects" */}
          <a href="/">Investors</a> {/* Change "Books" to "Investors" */}
          <a href="/">About</a> {/* Change "Info" to "About" */}
          <a href="/">Contact</a> {/* Change "Newsletter" to "Contact" */}
        </nav>
      </header>
    <br/>
    <br/>
      <main>
        <div className="home-page">
          <div className="block">
            <h1>Welcome to AgriFunding!</h1> {/* Change the title */}
            <p className="intro">We empower farmers and agricultural projects with financial support.</p> {/* Change the introductory text */}
            <p>Explore our available funding options and kickstart your agricultural dreams.</p> {/* Change the additional text */}
            <a href="/" className="button">Browse Projects</a> {/* Change the button text and link */}
          </div>
          <div className="block">
            <img src="https://img.freepik.com/free-vector/hand-drawn-farming-profession_23-2148885540.jpg?size=626&ext=jpg" alt="Agricultural Funding Illustration" className='image'/> {/* Change the image */}
          </div>
        </div>
      </main>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>

      <footer>
        <small>All content &copy; AgriFunding. All rights reserved.</small> {/* Change the footer text */}
      </footer>
    </div>
  );
}

export default Home;