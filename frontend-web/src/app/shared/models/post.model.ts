export class Post {
    constructor(
        public creationDate: string,
        public title: string,
        public author: string,
        public published: boolean,
        public reviewStatus: string,
        public content: string,
        public id?: string,
    ) {}
}