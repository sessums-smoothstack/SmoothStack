import {Component} from "react";

class StripedRowOne extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className={"row"}>
                <div className={"col-lg-12 bg-primary py-3"}></div>
                <div className={"col-lg-12 bg-secondary py-3"}></div>
                <div className={"col-lg-12 bg-primary py-3"}></div>
            </div>
        );
    }

}

export default StripedRowOne