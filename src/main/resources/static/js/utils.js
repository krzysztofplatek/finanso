function showForm(formNumber) {
    document.getElementById("form1").style.display = "none";
    document.getElementById("form2").style.display = "none";
    document.getElementById("form3").style.display = "none";

    document.getElementById("form" + formNumber).style.display = "block";
}