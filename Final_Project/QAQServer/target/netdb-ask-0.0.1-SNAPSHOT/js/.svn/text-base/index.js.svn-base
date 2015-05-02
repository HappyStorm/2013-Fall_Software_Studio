/**
 * Javascript allows global variables/functions, which locate inside window.
 */
onload = function() {
	initAskButton();
	initUI();
	initNewCommentForm();
};

var initAskButton = function(){
	var askButton = document.getElementById('askbtn');
	var qtext = document.getElementById('askinput');
	askButton.onclick = function(e) {
		getAnswer();
	};
	qtext.onkeyup = function(e) {
		if(e.keyCode == 13) // enter pressed
			getAnswer();
	}; 
};

var initUI = function(){
	var defDiv = document.getElementById('answer');
	defDiv.onclick = function(){
		var defDiv = document.getElementById('answer');
		var defForm = document.getElementById('def-form');
		var askBar = document.getElementById('askbar');
		var def = document.getElementById('definition');
		addClass(defDiv, 'content-hidden');
		addClass(askbar, 'content-hidden');
		removeClass(defForm, 'content-hidden');
		defForm.definition.value = defDiv.innerHTML;
		def.style.height = 'auto';
        def.style.height = def.scrollHeight+'px';
        def.setSelectionRange(def.value.length, def.value.length);
        def.focus();
	};
	
	var defForm = document.getElementById('def-form');
	defForm.onsubmit = function(){
		var defDiv = document.getElementById('answer');
		var defForm = document.getElementById('def-form');
		defDiv.innerHTML = defForm.definition.value;
		addClass(defForm, 'content-hidden');
		var imgEl = document.getElementById('loading');
		removeClass(imgEl, 'content-hidden');
		var title = document.getElementById('asktitle');
		ajaxRequest('POST', encodeURI('definition'), {
			'Content-Type': 'application/json;charset=UTF-8'
		}, '{' + 
			// escape in case title and text contain chars sensitive to JSON parsing 
			'"title":"' + encodeURI(title.innerHTML) + '",' +
			'"description":"' + encodeURI(defDiv.innerHTML) + '"' +
		'}', function(status, headers, body) { // success callback
			var defDiv = document.getElementById('answer');
			var askBar = document.getElementById('askbar');
			var imgEl = document.getElementById('loading');
			removeClass(defDiv, 'content-hidden');
			removeClass(askbar, 'content-hidden');
			addClass(imgEl, 'content-hidden');
		}, function(status, headers, body) {  // error callback
			alert('error: ' + status);
		}, null); // run callbacks in global scope
		return false;
	};
	
	var preComment = document.getElementById('pre-comment');
	preComment.onfocus = function(){
		var commentForm = document.getElementById('comment-form');
		var commentTitle= document.getElementById('comment-title');
		addClass(this, 'content-hidden');
		removeClass(commentForm, 'content-hidden');
		commentTitle.focus();
		
	}
	
	var cancelButton = document.getElementById('cancel-button');
	cancelButton.onclick = function(){
		var defDiv = document.getElementById('answer');
		var askBar = document.getElementById('askbar');
		var defForm = document.getElementById('def-form');
		addClass(defForm, 'content-hidden');
		removeClass(defDiv, 'content-hidden');
		removeClass(askbar, 'content-hidden');
	}
};

var initNewCommentForm = function() {
	var commentForm = document.getElementById('comment-form');
	commentForm.onsubmit = function(){
		var listEl = document.getElementById('list-area');
		var commentForm = document.getElementById('comment-form');
		if (commentForm.elements['comment-title'].value=="" || commentForm.elements['comment-text'].value=="")
			return false;
		var commentLoading = document.getElementById('list-loading');
		addClass(listEl, 'content-hidden');
		removeClass(commentLoading, 'content-hidden');
		var preComment = document.getElementById('pre-comment');
		addClass(this, 'content-hidden');
		removeClass(preComment, 'content-hidden');
		
		var title = document.getElementById('asktitle');
		ajaxRequest('POST', encodeURI('comment'), {
			'Content-Type': 'application/json;charset=UTF-8'
		}, '{' + 
			// escape in case title and text contain chars sensitive to JSON parsing 
			'"title":"' + encodeURI(commentForm.elements['comment-title'].value) + '",' +
			'"text":"' + encodeURI(commentForm.elements['comment-text'].value) + '",' +
			'"defTitle":"' + encodeURI(title.innerHTML) + '"' +
		'}', function(status, headers, body) { // success callback
			var ans = eval('(' + body + ')');
			var listEl = document.getElementById('list-area');
			var commentForm = document.getElementById('comment-form');
			var commentEl = listEl.firstChild;
			if (hasClass(commentEl, "list-no-comment"))
				listEl.innerHTML = '<div id="list-top-bar"></div>';
			listEl.innerHTML += '<li>'
				// escape in case title and text contain chars sensitive to HTML parsing 
				+ '<div class="list-comment-id">' + ans.id + '</div>'
				+ '<div class="list-comment-title">' + escapeHtml(commentForm.elements['comment-title'].value) + '</div>' 
				+ '<div class="list-comment-text">' + escapeHtml(commentForm.elements['comment-text'].value) + '</div>'
				+ '<div class="list-comment-stamp">' + new Date(ans.TimeStamp).toString().split(/(GMT|UTC)/i)[0] + '</div>'
				+ '</li>';
			commentForm.elements['comment-title'].value = "";
			commentForm.elements['comment-text'].value = "";
			var commentLoading = document.getElementById('list-loading');
			addClass(commentLoading, 'content-hidden');
			removeClass(listEl, 'content-hidden');
		}, function(status, headers, body) {  // error callback
			alert('error: ' + status);
		}, null); // run callbacks in global scope
		return false;
	};
	
};

var getAnswer = function() {
	// indicate loading
	var question = document.getElementById('askinput').value;
	var answerEl = document.getElementById('answer');
	var commentArea = document.getElementById('comment-area');
	var definitionTitle = document.getElementById('definition-title');
	var displayArea = document.getElementById('display-area');
	addClass(answerEl, 'content-hidden');
	addClass(commentArea, 'content-hidden');
	addClass(definitionTitle, 'content-hidden');
	removeClass(displayArea, 'content-hidden');
	var imgEl = document.getElementById('loading');
	removeClass(imgEl, 'content-hidden');
	document.getElementById('asktitle').innerHTML = question; 
	if (question == '') {
		showAnswer('');
		return;
	}
	ajaxRequest('GET', encodeURI('definition/' + question), {
		'Accept': 'application/json'
	}, null, function(status, headers, body) { // success callback
		// show answer
		var ans = eval('(' + body + ')');
		showAnswer(ans.description);
	}, function(status, headers, body) {  // error callback
		switch (status) {
			case 404: 
				status404Handler();
			default:
				// do nothing
		}
	}, null); // run callbacks in global scope
	
	ajaxRequest('GET', encodeURI('comment/' + question), {
		'Accept': 'application/json'
	}, null, function(status, headers, body) { // success callback
		// show comments
		var comms = eval('(' + body + ')');
		showComments(comms);
	}, function(status, headers, body) {  // error callback
		switch (status) {
			case 404: 
				status404Handler();
			default:
				// do nothing
		}
	}, null);// run callbacks in global scope
};

var showAnswer = function(description) {
	var answerEl = document.getElementById('answer');
	var displayArea = document.getElementById('display-area');
	var commentArea = document.getElementById('comment-area');
	var definitionTitle = document.getElementById('definition-title');
	var askTitle = document.getElementById('asktitle');
	var definitionTitle = document.getElementById('definition-title');
	definitionTitle.innerHTML = '&nbsp;Definition of '+askTitle.innerHTML+' &nbsp;';
	answerEl.innerHTML = escapeHtml(decodeURI(description));
	removeClass(answerEl, 'content-hidden');
	removeClass(commentArea, 'content-hidden');
	removeClass(definitionTitle, 'content-hidden');
	removeClass(displayArea, 'content-hidden');
	// hide loading indicator
	var imgEl = document.getElementById('loading');
	addClass(imgEl, 'content-hidden');
};

var status404Handler = function() {
	showAnswer('Sorry, I don\'t understand your question!');
};

var showComments = function(comment) {
	var listEl = document.getElementById('list-area');
	if(!comment || comment.length == 0) {
		listEl.innerHTML = '<li class="list-no-comment">There is no comment here!</li>';
		return;
	}
	var htmlFrag = '<div id="list-top-bar"></div>';
	for(var i = 0; i < comment.length; i++) {
		htmlFrag += '<li>'
				+ '<span class="list-comment-id">' + comment[i].id + '</span>' 
				// escape in case title and text contain chars sensitive to HTML parsing 
				+ '<div class="list-comment-title">' + escapeHtml(decodeURI(comment[i].title)) + '</div>' 
				+ '<div class="list-comment-text">' + escapeHtml(decodeURI(comment[i].text)) + '</div>'
				+ '<div class="list-comment-stamp">' + new Date(comment[i].stamp).toString().split(/(GMT|UTC)/i)[0] + '</div>'
				+ '</li>';
	}
	// update html
	listEl.innerHTML = htmlFrag;
	// add handlers
	var commentEl = listEl.firstChild;
	var i = 0;
	while(commentEl) {
		commentEl.onmouseover = function(el) {
			return function() { // closure
				addClass(el, 'list-comment-over');
			};
		}(commentEl);
		commentEl = commentEl.nextSibling;
	}
}