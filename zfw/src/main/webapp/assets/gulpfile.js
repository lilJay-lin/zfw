
var gulp = require("gulp"),
ugify = require("gulp-uglify"),
minify = require("gulp-minify-css"),
concat = require("gulp-concat"),
less = require("gulp-less"),
autoprefixer = require('gulp-autoprefixer'),
rename = require("gulp-rename");
uglify = require("gulp-uglify");

//gulp.task("build:test:css",function(){
//	return gulp.src("./staticPages/less/mi/*.less")
//	.pipe(less())
//	.pipe(autoprefixer())
//	.pipe(concat("mi.css"))
//	.pipe(gulp.dest("./staticPages/css"))
//	.pipe(minify())
//	.pipe(gulp.dest("./css"));
//});


gulp.task("build:ui:css",function(){
	
	return gulp.src("./staticPages/less/ui/*.less")
	.pipe(less())
	.pipe(autoprefixer())
	.pipe(concat("ui.css"))
	.pipe(gulp.dest("./staticPages/css"))
	.pipe(minify())
	.pipe(gulp.dest("./css"));
});

gulp.task("build:mi:css",function(){
	return gulp.src("./staticPages/less/mi/*.less")
	.pipe(less())
	.pipe(autoprefixer())
	.pipe(concat("mi.css"))
	.pipe(gulp.dest("./staticPages/css"))
	.pipe(minify())
	.pipe(gulp.dest("./css"));
});

gulp.task("build:common:css",function(){
	return gulp.src("./staticPages/less/*.less")
	.pipe(less())
	.pipe(autoprefixer())
	.pipe(concat("common.css"))
	.pipe(gulp.dest("./staticPages/css"))
	.pipe(minify())
	.pipe(gulp.dest("./css"));
});

gulp.task("build:ui:js",function(){
	return gulp.src("./staticPages/js/ui/*.js") 
	.pipe(rename({suffix: '.min'}))   
    .pipe(uglify())    
	.pipe(gulp.dest("./js/ui"));
});

gulp.task("build:mi:js",function(){
	return gulp.src("./staticPages/js/mi/*.js") 
	.pipe(rename({suffix: '.min'}))  
    .pipe(uglify())   
	.pipe(gulp.dest("./js/mi"));
});

gulp.task("build:common:js",function(){
	return gulp.src("./staticPages/js/*.js") 
	.pipe(rename({suffix: '.min'}))  
    .pipe(uglify())   
	.pipe(gulp.dest("./js"));
});

gulp.task("default",function(){
//	gulp.run("build:test:css");
	gulp.run("build:ui:css");
	gulp.watch("./staticPages/less/ui/*.less",function(){
		gulp.run("build:ui:css");
	});

	gulp.run("build:mi:css");
	gulp.watch("./staticPages/less/mi/*.less",function(){
		gulp.run("build:mi:css");
	});

	gulp.run("build:common:css");
	gulp.watch("./staticPages/less/*.less",function(){
		gulp.run("build:common:css");
	});

	gulp.run("build:ui:js");
	gulp.watch("./staticPages/js/ui/*.js",function(){
		gulp.run("build:ui:js");
	});

	gulp.run("build:mi:js");
	gulp.watch("./staticPages/js/mi/*.js",function(){
		gulp.run("build:mi:js");
	});

	gulp.run("build:common:js");
	gulp.watch("./staticPages/js/*.js",function(){
		gulp.run("build:common:js");
	});

});
