import {Component} from "react";

class FunButtonOne extends Component {

    constructor(props) {
        super(props);
        this.state = {
            clicked: false,
            title: props.title,
        }
    }

    handleClick() {
        this.setState({
            title: "Something else",
        });
    }

    render() {
        return (
            <button className={"btn btn-primary my-5"} onClick={() => this.handleClick()}>
                {this.state.title}
            </button>
        );
    }
}

export default FunButtonOne;