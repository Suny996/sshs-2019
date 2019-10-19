/*
 * Copyright 2013 The Polymer Authors. All rights reserved.
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file.
 */
(function() {
  var thisFile = 'pointerevents.js';
  var scopeName = 'PointerEventsPolyfill';
  var modules = [
    'src/boot.js',
    'src/touch-action.js',
    'src/PointerEvent.js',
    'src/pointermap.js',
    'src/dispatcher.js',
    'src/installer.js',
    'src/mouse.js',
    'src/touch.js',
    'src/ms.js',
    'src/platform-events.js',
    'src/capture.js'
  ];

  window[scopeName] = {
    entryPointName: thisFile,
    modules: modules
  };

  var script = document.querySelector('script[src $= "' + thisFile + '"]');
  if (!script) {
    return;
  }
  var src = script.attributes.src.value;
  var basePath = src.slice(0, src.indexOf(thisFile));

  if (!window.PolymerLoader) {
    var path = basePath + '../tools/loader/loader.js';
    document.write('<script src="' + path + '"></script>');
  }

  document.write('<script>PolymerLoader.load("' + scopeName + '")</script>');

})();


/*
 * Copyright 2013 The Polymer Authors. All rights reserved.
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file.
 */
/**
 * @module PointerGestures
 */
(function() {
  var thisFile = 'pointergestures.js';
  var scopeName = 'PointerGestures';
  var modules = [
    'src/PointerGestureEvent.js',
    'src/initialize.js',
    '../WeakMap/weakmap.js',
    'src/pointermap.js',
    'src/dispatcher.js',
    'src/hold.js',
    'src/track.js',
    'src/flick.js',
    'src/pinch.js',
    'src/tap.js',
    'src/registerScopes.js'
  ];

  window[scopeName] = {
    entryPointName: thisFile,
    modules: modules
  };

  var script = document.querySelector('script[src $= "' + thisFile + '"]');
  if (!script) {
    return;
  }
  var src = script.attributes.src.value;
  var basePath = src.slice(0, src.indexOf(thisFile));

  if (!window.PointerEvent) {
    document.write('<script src="' + basePath + '../PointerEvents/pointerevents.js"></script>');
  }

  if (!window.PolymerLoader) {
    var path = basePath + '../tools/loader/loader.js';
    document.write('<script src="' + path + '"></script>');
  }
  document.write('<script>PolymerLoader.load("' + scopeName + '")</script>');
})();