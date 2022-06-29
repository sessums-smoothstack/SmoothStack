import React, {Component} from "react";
import Clock from '../clock/Clock';


class Card extends Component {
    constructor(props) {
        super(props);
        this.cardTitle = props.title;
        this.cardText = props.text;
    }

    render() {
        return (
            <div className={"card my-5 border-primary"}>
                <div className={"card-body text-center"}>
                    <h1>{this.cardTitle}</h1>
                    <p>{this.cardText}</p>
                    <Clock locale='en-US' timeZoneName='short'/>

                    <Clock locale='en-GB' timeZone='MST' timeZoneName='short'/>

                    <Clock locale='en-US' timeZone='UTC' timeZoneName='short'/>
                </div>
            </div>
        );
    }
}

export default Card;