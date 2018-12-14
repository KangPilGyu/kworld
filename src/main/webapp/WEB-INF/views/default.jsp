<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script>
 $(document).ready(function(){
	 $(document).snowfall({deviceorientation : true, round : true, minSize: 1, maxSize:8,  flakeCount : 250});
	 });  
 </script>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="/resources/images/christmas.jpg" style="height: 500px" alt="Image">
        <div class="carousel-caption">
          <h3>Merry ChristMas</h3>
          <p>Hi Kworld</p>
        </div>      
      </div>

      <div class="item">
         <img src="/resources/images/church.jpg" style="height: 500px" alt="Image">
        <div class="carousel-caption">
          <h3>Merry ChristMas</h3>
          <p>Hello</p>
        </div>      
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>
  
  
  
<div class="container text-center">    
      
      <hr class="featurette-divider">
      
      <div class="row featurette" >
        <div class="col-md-7">
          <h2 class="featurette-heading">Hi! WelCome to Kworld<span class="text-muted">Merry ChristMas</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
<img class="featurette-image img-responsive center-block" src="/resources/images/tree2.jpg">        </div>
      </div>
      
            <hr class="featurette-divider">
            
            <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
          <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
        
          <img class="featurette-image img-responsive center-block" src="/resources/images/santa.jpg">        </div>
      </div>
            
          <hr class="featurette-divider">
                  
                  <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading text-muted">Please Donation Now</h2>
          <p class="lead text-muted">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
        </div>
        <div class="col-md-5">
        <img class="featurette-image img-responsive center-block" src="/resources/images/donation.jpg"> 
        </div>
      </div>
      
        <iframe class="featurette-image img-responsive center-block" width="500" height="500" style="display:none;"
			src="https://www.youtube.com/embed/yXQViqx6GMY?controls=0&autoplay=1">
		</iframe>
      
</div><br>

