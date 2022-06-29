const canvas = document.getElementById("canvas");
const context = canvas.getContext("2d");
context.globalAlpha = 0.5;

const cursor = {
    x: innerWidth / 2,
    y: innerHeight / 2,
};

let particlesArray = [];




addEventListener("mousemove", (e) => {
    cursor.x = e.clientX;
    cursor.y = e.clientY;
});

addEventListener(
    "touchmove",
    (e) => {
        e.preventDefault();
        cursor.x = e.touches[0].clientX;
        cursor.y = e.touches[0].clientY;
    },
    { passive: false }
);

addEventListener("resize", () => setSize());

function generateParticles(amount) {
    for (let i = 0; i < amount; i++) {
        particlesArray[i] = new Particle(
            innerWidth / 2,
            innerHeight / 2,
            4,
            generateColor(),
            0.02
        );
    }
}

function generateColor() {
    let hexSet = "0123456789ABCDEF";
    let finalHexString = "#";
    for (let i = 0; i < 6; i++) {
        finalHexString += hexSet[Math.ceil(Math.random() * 15)];
    }
    return finalHexString;
}

function setSize() {
    canvas.height = innerHeight;
    canvas.width = innerWidth;
}

function Particle(x, y, particleTrailWidth, strokeColor, rotateSpeed) {
    this.x = x;
    this.y = y;
    this.particleTrailWidth = particleTrailWidth;
    this.strokeColor = strokeColor;
    this.theta = Math.random() * Math.PI * 2;
    this.rotateSpeed = rotateSpeed;
    this.t = Math.random() * 150;

    this.rotate = () => {
        const ls = {
            x: this.x,
            y: this.y,
        };
        this.theta += this.rotateSpeed;
        this.x = cursor.x + Math.cos(this.theta) * this.t;
        this.y = cursor.y + Math.sin(this.theta) * this.t;
        context.beginPath();
        context.lineWidth = this.particleTrailWidth;
        context.strokeStyle = this.strokeColor;
        context.moveTo(ls.x, ls.y);
        context.lineTo(this.x, this.y);
        context.stroke();
    };
}

function anim() {
    requestAnimationFrame(anim);

    context.fillStyle = "rgba(0,0,0,0.05)";
    context.fillRect(0, 0, canvas.width, canvas.height);

    particlesArray.forEach((particle) => particle.rotate());
}

function clear() {
    context.setTransform(0, 0, 0, 0, 0, 0);
    context.clearRect(0,0, context.canvas.width, context.canvas.height);

}

function start() {
    generateParticles(200);
    setSize();
    anim();
}

function addCardToRow() {
    let row = document.getElementById("cardRow");
    let col = document.createElement("div");
    let card = document.createElement("div");
    let cardBody = document.createElement("div");
    let title1 = document.createElement("h5");
    let title2 = document.createElement("h5");
    let cardText = document.createElement("p");
    let button = document.createElement("button");
    col.classList.add("col-lg-4");
    card.classList.add("card", "bg-light",  "my-2");
    cardBody.classList.add("card-body", "text-center");
    title1.classList.add("card-title");
    title1.innerText = "I'm a card!";
    title2.classList.add("card-title");
    title2.innerText = "Something here!";
    cardText.classList.add("card-text");
    cardText.innerText = "There are many variations of passages of Lorem Ipsum available, " +
        "but the majority have suffered alteration in some form, " +
        "by injected humour, "+
        "or randomised words which don't look even slightly believable.";
    button.classList.add("btn-sm", "btn-primary");
    button.innerText = "Click Me!";
    button.onclick = addCardToRow;

    cardBody.appendChild(title1);
    cardBody.appendChild(title2);
    cardBody.appendChild(cardText);
    cardBody.appendChild(button);

    card.appendChild(cardBody);

    col.appendChild(card);
    row.appendChild(col);
}

