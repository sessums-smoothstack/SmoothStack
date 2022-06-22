const defaultText = "Click the button for a fact!";

 async function getFact() {
    let factResponse = await fetch("https://uselessfacts.jsph.pl/random.json?language=en");
    let factBody = await factResponse.json();
    let fact = factBody.text;
    return fact;
}

async function clicked() {
    document.getElementById("factText").innerText = await getFact();
}

async function clipboardCopy() {
     let fact = document.getElementById("factText").innerText;
     if(fact === defaultText) {
         fact = await getFact();
         document.getElementById("factText").innerText = fact;
     }
     navigator.clipboard.writeText(fact);
}