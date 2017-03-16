function changeColor() {
    if ($("input[name=it2]").val() != null) {
       if (($("input[name=it2]").val().length > 0) && ($("input[name=it2]").val().length < 3)) {
            $("input[name=it2]").css("color", "magenta");
        }
      else if (($("input[name=it2]").val().length > 3) && ($("input[name=it2]").val().length < 6)) {
            $("input[name=it2]").css("color", "red");
        }
        else if (($("input[name=it2]").val().length >= 6) && ($("input[name=it2]").val().length < 9)) {
            $("input[name=it2]").css("color", "yellow");
        }
         else if (($("input[name=it2]").val().length >= 9) && ($("input[name=it2]").val().length < 12)) {
            $("input[name=it2]").css("color", "maroon");
        }
         else if (($("input[name=it2]").val().length >= 12) && ($("input[name=it2]").val().length < 15)) {
            $("input[name=it2]").css("color", "teal");
        }
         else if (($("input[name=it2]").val().length >= 15) && ($("input[name=it2]").val().length < 18)) {
            $("input[name=it2]").css("color", "blue");
        }
        else {
            $("input[name=it2]").css("color", "green");
        }
    }
}