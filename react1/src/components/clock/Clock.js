import React, { Component } from 'react';

class Clock extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            date: new Date(),
            locale: props.locale,
            options: {
                timeZone: props.timeZone,
                timeZoneName: props.timeZoneName
            }
        };
    }

    componentDidMount() {
        this.timerID = setInterval(
            () => this.tick(),
            1000
        );
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    tick() {
        this.setState({
            date: new Date()
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">It is {this.state.date.toLocaleTimeString(this.state.locale, this.state.options)}.</h2>
            </div>
        );
    }
}

export default Clock;