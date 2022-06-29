import {Component} from "react";

class FunButtonTwo extends Component {

    constructor(props) {
        super(props);
        this.state = {
            clicked: false,
            title: props.title,
        }
    }

    handleClick() {
        this.setState({
            title: "is here",
        });
    }

    render() {
        return (
            <button className={"btn btn-secondary my-5"} onClick={() => this.handleClick()}>
                {this.state.title}
            </button>
        );
    }
}
export default FunButtonTwo;