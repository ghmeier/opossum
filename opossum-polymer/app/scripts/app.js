(function (document) {
  'use strict';

  // Grab a reference to our auto-binding template
  // and give it some initial binding values
  // Learn more about auto-binding templates at http://goo.gl/Dx1u2g
  var app = document.querySelector('#app');
  app.appName = 'Opossum';
  app.server = 'https://opossum.herokuapp.com';
  console.log(window.location.href);
  if ((window.location.href === 'http://localhost:9000/' || window.location.href==="https://opossum.firebaseapp.com/") && localStorage.name){
    //console.log(localStorage.name);
    window.location.href = 'profile.html';
  }

  // Listen for template bound event to know when bindings
  // have resolved and content has been stamped to the page
  app.addEventListener('template-bound', function() {

  });

// wrap document so it plays nice with other libraries
// http://www.polymer-project.org/platform/shadow-dom.html#wrappers
})(wrap(document));
